package controller.products;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.LogicException;
import common.utils.Utils;
import model.bo.ProductBO;
import model.bo.UserBO;
import model.business.products.ProductBusiness;
import model.business.products.ProductBusinessImpl;

@Controller
@RequestMapping(value = "/trang-chu")
public class ProductControllerImpl {

	@Autowired
	private ProductFormValidator productFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productFormValidator);
	}

	@GetMapping(value = { "/" })
	public ModelAndView homePage(ModelMap model, HttpServletRequest request, HttpSession session) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("index");
	}

	@GetMapping(value = { "/thong-tin-tai-khoan" })
	public ModelAndView acountCFInfor(ModelMap model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirAtt) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		ProductBusiness productBusiness = new ProductBusinessImpl();
		try {
			List<ProductBO> listProductBO = productBusiness.getAllProduct();
			model.addAttribute("productData", listProductBO);
		} catch (LogicException e) {
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(null, e.getErrorCode());
			redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
			return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan");
		}

		return new ModelAndView("thong-tin-tai-khoan", model);
	}

	// them moi
	@GetMapping(value = { "/thong-tin-tai-khoan/them-moi" })
	public ModelAndView acountCFInsertHandler(ModelMap model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirAtt) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		ProductBO productBO = new ProductBO();
		String update = "Đăng ký";
		model.addAttribute("update", update);
		model.addAttribute("mode", "I");
		model.addAttribute("product", productBO);
		return new ModelAndView("them-moi");
	}

	// them moi
	@PostMapping(value = { "/thong-tin-tai-khoan/them-moi"})
	public ModelAndView acountCFInsert(@RequestParam("ingameImagefile") MultipartFile productVipIngameImage,
			@RequestParam("productImageFile") MultipartFile[] productImage,
			@ModelAttribute("product") @Valid ProductBO productBO, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirAtt) {

		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");

		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		if (result.hasErrors()) {

			model.addAttribute("product", productBO);
			return new ModelAndView("them-moi");
		} else {
			try {

				// get current date time
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String currentDateTime = now.format(dateTimeFormat);

				// get name file
				List<Map<String, List<String>>> dataName = new ArrayList<Map<String, List<String>>>();
				dataName = uploadImage(request, productVipIngameImage, productImage);

				// set value Product
				productBO.setProductVipIngameImage(dataName.get(0).get("ListFileNameVipIngameImage").get(0));
				productBO.setProductCreateDate(currentDateTime);
				productBO.setProductUserAdd(boSesson.getUserName());

				ProductBusiness productBusiness = new ProductBusinessImpl();

				List<String> listProductImage = new ArrayList<String>();
				listProductImage = dataName.get(0).get("listProductImage");
				int imageId = 0;
				for (int i = 0; i < listProductImage.size(); i++) {
					imageId = imageId + 1;
					productBO.setProductImageId(imageId);
					productBO.setProductImage(listProductImage.get(i));
					productBusiness.insertProduct(productBO);
				}

				redirAtt.addFlashAttribute("SuccessMesage", "Đăng ký thông tin tài khoản CF thành công.");

			} catch (LogicException e) {

				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, e.getErrorCode());
				redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
				model.addAttribute("product", productBO);
				redirAtt.addFlashAttribute("SuccessMesage", "");
				return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/them-moi");
			}
		}

		return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/them-moi");
	}

	// Chinh sua
	@GetMapping(value = { "/thong-tin-tai-khoan/chinh-sua" })
	public ModelAndView acountCFUpdateHandler(@RequestParam("productCode") String productCode, ModelMap model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirAtt) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		// khởi tạo giá trị
		List<ProductBO> listProductBO = new ArrayList<ProductBO>();
		ProductBO productBO = new ProductBO();
		ProductBusiness productBusiness = new ProductBusinessImpl();
		String update = "Chỉnh Sửa";

		try {
			listProductBO = productBusiness.getProductByCode(productCode);
		} catch (LogicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> listProductImage = new ArrayList<String>();

		for (int i = 0; i < listProductBO.size(); i++) {
			listProductImage.add(listProductBO.get(i).getProductImage());
		}

		// setting giá trị cho BO
		productBO.setProductCode(listProductBO.get(0).getProductCode());
		productBO.setProductTitle(listProductBO.get(0).getProductTitle());
		productBO.setProductVipNumber(listProductBO.get(0).getProductVipNumber());
		productBO.setProductVipIngameLevel(listProductBO.get(0).getProductVipIngameLevel());
		productBO.setProductPrice(listProductBO.get(0).getProductPrice());
		productBO.setProductInfo(listProductBO.get(0).getProductInfo());

		// setting model
		model.addAttribute("update", update);
		model.addAttribute("mode", "U");
		model.addAttribute("product", new ProductBO());
		model.addAttribute("productBO", productBO);
		model.addAttribute("productImageName", listProductImage);
		model.addAttribute("productImage", listProductBO);
		return new ModelAndView("them-moi");
	}

	// chinh sua
	@PostMapping(value = { "/thong-tin-tai-khoan/chinh-sua" })
	public ModelAndView acountCFUpdate(@RequestParam("ingameImagefile") MultipartFile productVipIngameImage,
			@RequestParam("productImageFile") MultipartFile[] productImage,
			@ModelAttribute("product") @Valid ProductBO productBO, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirAtt) {

		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");

		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		if (result.hasErrors()) {

			model.addAttribute("product", productBO);
			return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/chinh-sua?productCode="+productBO.getProductCode());
		} else {
			try {

				// get current date time
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String currentDateTime = now.format(dateTimeFormat);

				// get name file
				List<Map<String, List<String>>> dataName = new ArrayList<Map<String, List<String>>>();
				
				dataName = uploadImage(request, productVipIngameImage, productImage);
				
				ProductBusiness productBusiness = new ProductBusinessImpl();

				List<String> listProductImage = new ArrayList<String>();
				listProductImage = dataName.get(0).get("listProductImage");
				
				List<ProductBO> listProductBO = new ArrayList<ProductBO>();
				
				listProductBO = productBusiness.getProductByCode(productBO.getProductCode());
				int imageId = 0;
				productBO.setProductUpdateDate(currentDateTime);
				productBO.setProductUserUpdate(boSesson.getUserName());
				
				if(listProductBO.size() > 0 && productVipIngameImage.isEmpty()) {
					imageId = listProductBO.get(listProductBO.size()- 1).getProductImageId();
					productBO.setProductVipIngameImage(listProductBO.get(0).getProductVipIngameImage());
					productBO.setProductCreateDate(listProductBO.get(0).getProductCreateDate());
					productBO.setProductUserAdd(listProductBO.get(0).getProductUserAdd());
					for(int i = 0; i< listProductBO.size(); i++) {
						productBO.setProductImageId(listProductBO.get(i).getProductImageId());
						productBO.setProductImage(listProductBO.get(i).getProductImage());
						productBusiness.updateProduct(productBO);
					}
					
				}else {
					// set value Product
					productBO.setProductVipIngameImage(dataName.get(0).get("ListFileNameVipIngameImage").get(0));
					productBO.setProductCreateDate(currentDateTime);
					productBO.setProductUserAdd(boSesson.getUserName());
				}
				
				for (int i = 0; i < listProductImage.size(); i++) {
					if(listProductImage.get(i) !="") {
					imageId = imageId + 1;
					productBO.setProductImageId(imageId);
					productBO.setProductImage(listProductImage.get(i));
					
					productBusiness.insertProduct(productBO);
					}
				}
				
				
				redirAtt.addFlashAttribute("SuccessMesage", "Chỉnh sửa thông tin tài khoản CF thành công.");

			} catch (LogicException e) {
				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, e.getErrorCode());
				redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
				redirAtt.addFlashAttribute("product", productBO);
				redirAtt.addFlashAttribute("SuccessMesage", "");
				return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/chinh-sua?productCode="+productBO.getProductCode());
			}
		}

		return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/chinh-sua?productCode="+productBO.getProductCode());
	}
	
	@PostMapping(value = { "/thong-tin-tai-khoan/xoa"}, params = {"image"})
	public ModelAndView acountCFXoa(@RequestParam("image") String productImageId,
			@ModelAttribute("product") ProductBO productBO, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirAtt) {

		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");

		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}
		
		ProductBusiness productBusiness = new ProductBusinessImpl();
		
		try {
			List<ProductBO> listProductBO = new ArrayList<ProductBO>();
			
			listProductBO = productBusiness.getProductByCode(productBO.getProductCode());
			
			if(listProductBO.size() > 1) {
				productBusiness.deleteProductByKey(productBO.getProductCode(), Integer.parseInt(productImageId));
			}else {
				productBO.setProductImage("");
				productBO.setProductImageId(Integer.parseInt(productImageId));;
				productBusiness.updateProduct(productBO);
			}
				
		} catch (LogicException e) {
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(null, e.getErrorCode());
			redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
			redirAtt.addFlashAttribute("product", productBO);
			redirAtt.addFlashAttribute("SuccessMesage", "");
			return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/chinh-sua?productCode="+productBO.getProductCode());
		}
		
		return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/chinh-sua?productCode="+productBO.getProductCode());
	}
	
	// upload
	private static List<Map<String, List<String>>> uploadImage(HttpServletRequest request,
			MultipartFile productVipIngameImage, MultipartFile[] productImage) throws LogicException {

		List<Map<String, List<String>>> dataName = new ArrayList<Map<String, List<String>>>();
		Map<String, List<String>> mapValue = new HashMap<String, List<String>>();

		// Thu muc goc upload
		String uploadRootPath = request.getServletContext().getRealPath("/upload");
		File uploadFileDir = new File(uploadRootPath);

		// tao thu muc goc neu thu muc chua ton tai
		if (!uploadFileDir.exists()) {
			uploadFileDir.mkdirs();
		}

		List<String> listFileNameVipIngameImage = new ArrayList<String>();
		// upload productVipIngameImage
		String fileNameVipIngameImage = Utils.uploadFile(uploadFileDir, productVipIngameImage);
		listFileNameVipIngameImage.add(fileNameVipIngameImage);
		mapValue.put("ListFileNameVipIngameImage", listFileNameVipIngameImage);

		// upload file productImage

		List<String> listProductImage = new ArrayList<String>();
		for (MultipartFile productImageFile : productImage) {

			// thuc hien upload
			String fileNameUpdload = Utils.uploadFile(uploadFileDir, productImageFile);
			listProductImage.add(fileNameUpdload);
		}
		mapValue.put("listProductImage", listProductImage);

		dataName.add(mapValue);
		
		return dataName;
	}
}
