import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApiTest {

    @Test
    public void verificarDepartamentosMercadoLibre_04() {
        CloseableHttpClient httpClientCustom = HttpClients.custom().disableCookieManagement().build();

        HttpGet request = new HttpGet("https://www.mercadolibre.com.ar/menu/departments");
        HttpResponse response;
        try {
            response = httpClientCustom.execute(request);
            HttpEntity entity = response.getEntity();
            String departmentsResponse = EntityUtils.toString(entity);
            Assert.assertTrue(departmentsResponse.contains("departments"), "No se encuentran departamentos");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new AssertionError("No se encuentran departamentos");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void obtenerTodasLasRazasDePerro_05() {
        CloseableHttpClient httpClientCustom = HttpClients.custom().disableCookieManagement().build();

        HttpGet request = new HttpGet("https://dog.ceo/api/breeds/list/all");
        HttpResponse response;
        try {
            response = httpClientCustom.execute(request);
            HttpEntity entity = response.getEntity();
            String breedsResponse = EntityUtils.toString(entity);
            Assert.assertTrue(breedsResponse.contains("kelpie"), "No se encuentran departamentos");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new AssertionError("No se encuentran departamentos");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
