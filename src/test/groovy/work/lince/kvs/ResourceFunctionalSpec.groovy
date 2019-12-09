package work.lince.kvs

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.kvs.model.Resource
import work.lince.kvs.repository.ResourceRepository

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResourceFunctionalSpec extends Specification {

    @Shared
    RESTClient client

    @LocalServerPort
    int port;

    @Autowired
    ResourceRepository resourceRepository

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "get with success #id"() {
        given:
            resourceRepository.save(
                new Resource(id: id, name: resourceName, key: resourceId, ttl: ttl, value: "{\"id\": \"${resourceId}\", \"name\": \"${name}\"}")
            )

        when:
            def result = client.get(path: "resources/${resourceName}/${resourceId}")

        then:
            result != null
            result.status == 200
            result.data.id == resourceId
            result.data.name == name

        where:
            id          | resourceName | resourceId | ttl  | name
            "uuid-0123" | "cache1"     | "a12"      | 120L | "name 1"
            "uuid-1234" | "cache1"     | "a13"      | 0    | "name 2"
            "uuid-2345" | "cache2"     | "a12"      | 120L | "name 3"
            "uuid-3456" | "cache2"     | "a13"      | 120L | "name 4"
            "uuid-4567" | "cache3"     | "a12"      | 120L | "name 5"

    }


    @Unroll
    def "put with success #resourceName/#resurlceId"() {
        given:
            def body = [
                id  : resourceId,
                name: name
            ]

        when:
            def result = client.put(path: "resources/${resourceName}/${resourceId}", body: body, headers: ["lince.user.name": userName])

        then:
            result != null
            result.status == HttpStatus.SC_OK
            result.data.id == resourceId
            result.data.name == name

        where:
            id          | resourceName | resourceId | ttl  | name     | userName
            "uuid-0123" | "cache1"     | "a12"      | 120L | "name 1" | null
            "uuid-1234" | "cache1"     | "a13"      | 0    | "name 2" | "asdf1234"
            "uuid-2345" | "cache2"     | "a12"      | 120L | "name 3" | "asdf1234"
            "uuid-3456" | "cache2"     | "a13"      | 120L | "name 4" | "asdf1234"
            "uuid-4567" | "cache3"     | "a12"      | 120L | "name 5" | "asdf1234"


    }

    @Unroll
    def "post with success #resourceName/#resurlceId"() {
        given:
            def body = [
                id  : resourceId,
                name: name
            ]

        when:
            def result = client.post(path: "resources/${resourceName}/${resourceId}", body: body, headers: ["lince.user.name": userName])

        then:
            result != null
            result.status == HttpStatus.SC_CREATED
            result.data.id == resourceId
            result.data.name == name

        where:
            id          | resourceName | resourceId | ttl  | name     | userName
            "uuid-0123" | "cache1"     | "a12"      | 120L | "name 1" | "asdf1234"
            "uuid-1234" | "cache1"     | "a13"      | 0    | "name 2" | "asdf1234"
            "uuid-2345" | "cache2"     | "a12"      | 120L | "name 3" | "asdf1234"
            "uuid-3456" | "cache2"     | "a13"      | 120L | "name 4" | "asdf1234"
            "uuid-4567" | "cache3"     | "a12"      | 120L | "name 5" | "asdf1234"


    }
}