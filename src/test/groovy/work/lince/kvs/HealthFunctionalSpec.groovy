package work.lince.kvs

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.kvs.model.Resource
import work.lince.kvs.repository.ResourceRepository

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthFunctionalSpec extends Specification {

    @Shared
    RESTClient client

    @LocalServerPort
    int port;

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "get Success #user"() {

        when:
            def result = client.get(path: "health", headers: ["lince.user.name": user])

        then:
            result != null
            result.data.status == "ok"
            result.data.now != null
            result.data.user == expectedUser

        where:
            user       | expectedUser
            null       | "anonymous"
            "zzz"      | "zzz"
            "asdf1234" | "asdf1234"

    }

}