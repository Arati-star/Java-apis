package in.ashokit.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;
import in.ashokit.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {
	@Autowired
	private ReportService service;
	@GetMapping("/plans")
	public ResponseEntity<List<String>>getPlanNames(){
	List<String> planNames=service.getUniquePlanNames();
	return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/Statuses")
	public ResponseEntity<List<String>>getPlanStatuses(){
	List<String> statuses=service.getUniquePlanStatuses();
	return new ResponseEntity<>(statuses,HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request)
	{
		List<SearchResponse> response=service.search(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response)throws Exception{
	response.setContentType("application/octet-stream");
	String headerkey="Content-Disposition";
	String headerValue="attachment;filename=data.xls";
	response.setHeader(headerkey, headerValue);
	service.generateExcel(response);
	}
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.pdf";
		response.setHeader(headerKey, headerValue);
		service.generatePdf(response);
	}
}
