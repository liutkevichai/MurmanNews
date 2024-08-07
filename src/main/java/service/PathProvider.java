package service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс PathProvider имеет статический метод getRelativePath для получения отдельных частей URI.
 */
public class PathProvider {
	
	/**
	* Функция для получения отдельной части URI, отделенной слэшами, в виде строки.
	* @param request - HTTP запрос
	* @param part - номер части с конца (-1 возвращает последний фрагмент, -2 предпоследний и т.д.)
	* @return возвращает фрагмент пути как строку<br>
	* Пример: запрос направлен по адресу myapp/some/resource, getRelativePath(request, -2) вернет "some".
	*/
	public static String getEndpoint(HttpServletRequest request, Integer part) {
		URI uri = null;
		try {
			uri = new URI(request.getRequestURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String[] pathArray = uri.toString().split("/");
		String relativePath;
		
		if ( pathArray.length > 1 ) {
			relativePath = pathArray[pathArray.length + part];
		} else { relativePath = pathArray[0]; }
		
		System.out.println("Endpoint " + relativePath);  //DEBUG
		return relativePath;
		
	}
	
}
