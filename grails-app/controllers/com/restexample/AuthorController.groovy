package com.restexample

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class AuthorController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Author.list(params), [status: OK]
    }

    @Transactional
    def save(Author authorInstance) {
        if (authorInstance == null) {
            render status: NOT_FOUND
            return
        }

        authorInstance.validate()
        if (authorInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        authorInstance.save flush:true
        respond authorInstance, [status: CREATED]
    }

    @Transactional
    def update(Author authorInstance) {
        if (authorInstance == null) {
            render status: NOT_FOUND
            return
        }

        authorInstance.validate()
        if (authorInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        authorInstance.save flush:true
        respond authorInstance, [status: OK]
    }

    @Transactional
    def delete(Author authorInstance) {

        if (authorInstance == null) {
            render status: NOT_FOUND
            return
        }

        authorInstance.delete flush:true
        render status: NO_CONTENT
    }
}
