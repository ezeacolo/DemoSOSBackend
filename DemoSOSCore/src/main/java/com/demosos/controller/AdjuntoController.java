package com.demosos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.demosos.domain.Adjunto;
import com.demosos.service.AdjuntoService;
import com.demosos.util.SecureResponse;

/**
 * 
 * @author DemoSOS
 * 
 */
@Controller
public class AdjuntoController {

	@Autowired
	AdjuntoService adjuntoService;

	/*@RequestMapping(value = "/incidentes/{id}/upload", method = RequestMethod.POST)
	@ResponseBody
	public SecureResponse uploadIncidentesFotos(
			MultipartHttpServletRequest request,
			@PathVariable("id") Long idTmp,
			@RequestHeader("token") String tokenEncoded) {

		SecureResponse result = null;

		try {

			final List<Adjunto> adjuntos = adjuntoService.upload(idTmp,
					tokenEncoded, request);

			result = SecureResponse.success(adjuntos);

		} catch (Exception ex) {
			ex.printStackTrace();
			result = SecureResponse.fail(ex);
		}

		return result;
	}*/
}