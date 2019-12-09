package work.lince.kvs.service

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.kvs.model.Resource
import work.lince.kvs.repository.ResourceRepository

@Ignore
class ResourceServiceSpec extends Specification {

    ResourceService service;

    def setup() {
        service = Spy(ResourceService)
        service.repository = Mock(ResourceRepository)
        service.authenticationService = Mock(AuthenticationService)

    }

    @Unroll
    def "verify with #title"() {
        given:
            1 * service.repository.save(_) >> { Resource value ->
                value.id = id
                return value
            }
            1 * service.authenticationService.getAuthenticatedUser() >> { user }
            def project = new Resource(
                title: title,
                status: status,
                owner: owner
            )
        when:
            def result = service.create(project)

        then:
            result != null
            result.id == id
            result.title == title
            result.owner == user
            result.status == ProjectStatus.CREATED

        where:
            title             | status               | owner      | user   | id
            "Project Title 1" | null                 | "asdfasdf" | "asdf" | 1L
            "Project Title 2" | ProjectStatus.CLOSED | null       | "qwer" | 2L
            "Project Title 3" | null                 | null       | "asdf" | 3L
            "Project Title 4" | ProjectStatus.CLOSED | "asdfasdf" | "qwer" | 4L


    }

}