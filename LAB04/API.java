package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class provider fub=nctions help send request to server and return response
 * Date 17/12/2020
 * @author phamh
 * @version 1.0
 *
 */
public class API {

	/**
	 * Help format DateTime
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Gelp log information to console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());
	
	/**
	 * Create the instance use to connect server
	 * @param url: The url of server you need to send request
	 * @param method: The method of request
	 * @param token: The hash code to identity user
	 * @return The instance represeting the connection to server
	 * @throws MalformedURLException, IOException, ProtocolException
	 */
	private static HttpURLConnection setUpConnection(String url, String method, String token)
			throws MalformedURLException, IOException, ProtocolException {
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}
	
	/**
	 * Create the instance use to connect server
	 * @param conn: The instance represeting the connection to server
	 * @return The response from server
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}

	/**
	 * Send get request with specified url and token to server
	 * @param url: The url of server you need to send request
	 * @param token: The hash code to identity user
	 * @return The response from server
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		HttpURLConnection conn = setUpConnection(url, "GET", token);
		
		return readResponse(conn);
	}

	int var;

	
	/**
	 * Send get request with specified url and token to server
	 * @param url: The url of server you need to send request
	 * @param data: The data sended to server to handle
	 * @param token: The hash code to identity user
	 * @return The response from server
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		allowMethods("PATCH");
		
		HttpURLConnection conn = setUpConnection(url, "GET", token);
		
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		return readResponse(conn);
	}

	
	/**
	 * This function allow call API with method: PUT, PATCH, ...(only Java 11)
	 * @deprecated only work with Java 11
	 * @param methods: The method need to allow
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
