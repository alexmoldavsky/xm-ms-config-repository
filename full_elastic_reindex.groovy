import groovy.json.JsonSlurper

import javax.servlet.http.HttpServletResponse
import java.util.stream.Collectors

def auth = { tenantName ->
    final HttpURLConnection connection = "http://${tenantName}.test.xm-online.com.ua/uaa/oauth/token".toString().toURL().openConnection()

    connection.setRequestProperty ("Authorization", 'Basic aW50ZXJuYWw6aW50ZXJuYWw=');

    connection.setDoOutput(true)
    connection.outputStream.withWriter { Writer writer ->
        writer << "grant_type=password&username=${tenantName}&password=P@ssw0rd".toString()
    }

    String response = connection.inputStream.withReader { Reader reader -> reader.text }
    return new JsonSlurper().parseText(response)
}

def runReindex = { tenantName, token ->
    final HttpURLConnection connection = "http://${tenantName}.test.xm-online.com.ua/entity/api/elasticsearch/index".toString().toURL().openConnection()
    connection.setRequestProperty ("Authorization", token);
    connection.setDoOutput(true)
    connection.outputStream.withWriter { Writer writer ->
        writer << ""
    }
    connection.inputStream.withReader { Reader reader ->  }
}

def tenantBlackList = ['lidoktest', 'tenant1155', 'tdemo2', 'tenant2', 'tenant3', 'tenant4', 'tenant5', 'tenant1',
                       'aaaaaaa', 'aaa', 'test1', 'testaccess', 'test_privilege', 'okoval', 'tenant1523536685216',
        'dont', 'rrrrrr', 'tenant1450']

def tenantList = new JsonSlurper().parseText(new File("./config/tenants/tenants-list.json").text)
List tenantKeys = tenantList.entity.stream().map{it.name}.collect(Collectors.toList())
tenantKeys.stream().filter{!tenantBlackList.contains(it)}.forEach{
    //http://compass.test.xm-online.com.ua/uaa/oauth/token?grant_type=password&username=paypoduser@yopmail.com&password=P@ssw0rd

    try {
        def token = "Bearer ${auth(it).access_token}"
        runReindex(it, token)
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000)
            println "run reindex... ${i}"
        }
        println "$it OK"
    } catch (Exception e) {
        e.printStackTrace()
        println "$it FAIL"
    }
}
