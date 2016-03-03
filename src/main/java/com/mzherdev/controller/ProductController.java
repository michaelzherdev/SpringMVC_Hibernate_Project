package com.mzherdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mzherdev.model.Product;
import com.mzherdev.service.ProductService;

@Controller
public class ProductController {

//	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	@Autowired
	private ProductService productService;

//	@Autowired
//	private SaleService saleService;
//
//	@Autowired
//	private OrderItemService orderItemService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/products";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String showAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products/list";
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String editProduct(
			@ModelAttribute("productForm") @Validated Product product,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "products/productform";
		} else {

			// Add message to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			if (product.isNew()) {
				redirectAttributes.addFlashAttribute("msg",
						"Product Added Successfully!");
				productService.add(product);
			} else {
				redirectAttributes.addFlashAttribute("msg",
						"Product Updated Successfully!");
				productService.edit(product);
			}
			return "redirect:/products/" + product.getId();

		}
	}

	// show add product form
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String showAddProductForm(Model model) {
		Product product = new Product();
		product.setName("");
		product.setPrice(0.0);
		model.addAttribute("productForm", product);
		return "products/productform";

	}

	// show update form
	@RequestMapping(value = "/products/{id}/update", method = RequestMethod.GET)
	public String showUpdateProductForm(@PathVariable("id") int id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("productForm", product);
		return "products/productform";

	}

	@RequestMapping(value = "/products/{id}/delete", method = RequestMethod.POST)
	public String deleteProduct(@PathVariable("id") int id,
			final RedirectAttributes redirectAttributes) {
		try {
			productService.delete(id);
		} catch (Exception e) {
			return "exception";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Product is deleted!");

		return "redirect:/products";

	}

	// show user
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable("id") int id, Model model) {

		Product product = productService.getProduct(id);
		if (product == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Product not found");
		}
		model.addAttribute("product", product);

		return "products/show";

	}

	
}
