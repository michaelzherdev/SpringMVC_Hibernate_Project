package com.mzherdev.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mzherdev.model.OrderItem;
import com.mzherdev.model.Product;
import com.mzherdev.model.Sale;
import com.mzherdev.service.OrderItemService;
import com.mzherdev.service.ProductService;
import com.mzherdev.service.SaleService;

@Controller
public class SaleController {
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleService saleService;

	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public String showAllSales(Model model) {
		model.addAttribute("sales", saleService.getAllSales());
		return "sales/saleslist";
	}

	// save sale
	@RequestMapping(value = "/sales", method = RequestMethod.POST)
	public String saveSale(@ModelAttribute("saleForm") @Validated Sale sale,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "sales/saleform";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg",
					"Sale Added Successfully!");

			if (!orderItems.isEmpty()) {
				double cost = 0.0;
				for (OrderItem i : orderItems) {
					cost += i.getProduct().getPrice() * i.getQuantity();
				}
				sale.setItems(orderItems);
				sale.setCost(cost);
				model.addAttribute("saleForm", sale);
				model.addAttribute("items", orderItems);

			}

			saleService.add(sale);

			List<Sale> sales = saleService.getAllSales();
			int saleId = 0;
			for (Sale sa : sales) {
				if (sa.getId() > saleId)
					saleId = sa.getId();
			}

			if (sale.getItems() != null) {
				for (OrderItem orderItem : sale.getItems()) {
					Sale s = saleService.getSale(saleId);
					orderItem.setSale(s);
					orderItemService.edit(orderItem);
				}
			}
			orderItems.clear();
		}

		populateDefaultModel(model);

		return "redirect:/sales/" + sale.getId();
	}

	// show add sale form
	@RequestMapping(value = "/sales/add", method = RequestMethod.GET)
	public String showAddSaleForm(Model model) {
		Sale sale;
		if (orderItems.isEmpty()) {
			sale = new Sale();
			List<OrderItem> items = new ArrayList<>();
			sale.setItems(items);
			model.addAttribute("saleForm", sale);
			model.addAttribute("items", items);
		} else {
			double cost = 0.0;
			for (OrderItem i : orderItems) {
				cost += i.getProduct().getPrice() * i.getQuantity();
			}

			sale = new Sale();
			sale.setItems(orderItems);
			sale.setCost(cost);
			model.addAttribute("saleForm", sale);
			model.addAttribute("items", orderItems);
		}

		return "sales/saleform";

	}

	// show add order item form
	@RequestMapping(value = "/sales/addOrderItem", method = RequestMethod.GET)
	public String showAddOrderItemForm(Model model) {

		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(null);
		orderItem.setQuantity(0);

		model.addAttribute("sale", new Sale());

		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("product", new Product());
		model.addAttribute("orderItemForm", orderItem);
		populateDefaultModel(model);

		return "sales/orderitemform";

	}

	// show sale
	@RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
	public String showSale(@PathVariable("id") int id, Model model) {

		Sale sale = saleService.getSale(id);

		System.out.println("showSale" + sale);
		if (sale == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Sale not found");
		}
		model.addAttribute("sale", sale);

		return "sales/showsale";

	}

	// save order item
	@RequestMapping(value = "/sales/addOrderItem", method = RequestMethod.POST)
	public String saveOrderItem(
			@ModelAttribute("orderItemForm") @Validated OrderItem orderItem,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "sales/orderitemform";
		} else {
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg",
					"Order Item Added Successfully!");
			orderItemService.add(orderItem);

			orderItems.add(orderItem);
			// POST/REDIRECT/GET
			return "redirect:/sales/add";

		}
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Product.class, new ProductEditor(
				productService));
	}

	@RequestMapping(value = "/sales/add")
	@ModelAttribute("items")
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	private void populateDefaultModel(Model model) {
		List<Integer> numbersList = new ArrayList<Integer>();
		numbersList.add(1);
		numbersList.add(2);
		numbersList.add(3);
		numbersList.add(4);
		numbersList.add(5);
		numbersList.add(6);
		numbersList.add(7);
		numbersList.add(8);
		numbersList.add(9);
		numbersList.add(10);
		model.addAttribute("numberList", numbersList);

		Map<String, String> productMap = new LinkedHashMap<String, String>();
		for (Product p : productService.getAllProducts()) {
			productMap.put(String.valueOf(p.getId()), p.getName());
		}
		model.addAttribute("productMap", productMap);

		// List<OrderItem> items = new ArrayList<>();
	}
}
