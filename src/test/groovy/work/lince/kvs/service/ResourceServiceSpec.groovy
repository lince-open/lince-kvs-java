package work.lince.kvs.service

import spock.lang.Specification
import work.lince.commons.authentication.AuthenticationService
import work.lince.kvs.repository.ResourceRepository

class ResourceServiceSpec extends Specification {

    ResourceService service;

    def setup() {
        service = Spy(ResourceService)
        service.repository = Mock(ResourceRepository)
        service.authenticationService = Mock(AuthenticationService)

    }

}