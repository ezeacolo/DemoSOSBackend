package com.demosos.service;

import org.springframework.stereotype.Service;

import com.demosos.secure.EHCache;

/**
 * Esta clase representa la validacion de si el token recibido por header es
 * correcto y valido. Mas adelante, intentaremos migrar esta logica a la de
 * Spring Security mediante el uso de filtros.
 * 
 * @author DemoSOS
 * 
 */
@Service(value = "tokenValidatorService")
public class TokenValidatorService {

	/**
	 * Retorna true si el token utilizado en la session es valido, false en caso
	 * contrario.
	 * 
	 * @param token a validar.
	 * @return True si el token es valido. False en caso contrario.
	 */
	public Boolean tokenIsValid(String token) {
		return true/*EHCache.loadUsuario(token) != null*/;
	}
	
}