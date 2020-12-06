package claudiordev.springboot.conversions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Controllers {
	String valueone = "";
	
	private IntToRomanService intToRomanService = new IntToRomanService();
	
	private RomanToIntService romanToIntService = new RomanToIntService();
	
	public String romanValue;
	
	
	@RequestMapping("")
	public String indexPage(Model model) {
		return "index.jsp";
	}
	
	/**
	 * Get POST request from Integer To Roman Conversion Form, located on index.jsp
	 * @param integerValue, value return from input
	 * @param model, object to add back value to be shown on index.jsp
	 */
	@RequestMapping(value="/intToRoman", method = RequestMethod.POST)
	public String romanConversion(@ModelAttribute("integerValue")  String integerValue, Model model) {
		if (integerValue != "") {
			String romanValue = intToRomanService.romanNumber(Integer.parseInt(integerValue));
			model.addAttribute("romanConvertedValue",romanValue);
		}
	    return "index.jsp";
	}
	
	
	@RequestMapping(value="/romanToInt", method = RequestMethod.POST)
	public String integerConversion(@ModelAttribute("romanValue")  String romanValue,Model model) {
		if (romanValue != "") {
			Integer integerValue = romanToIntService.integerNumber(romanValue);
			model.addAttribute("integerConvertedValue",integerValue);
		}
		return "index.jsp";
	}

}
