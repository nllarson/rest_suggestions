package com.restexample

import grails.rest.Resource

class Genre {

	String name

    static constraints = {
		name nullable: false, blank: false, unique: true
    }
}
