package com.restexample

class Author {

	String firstName
	String lastName
	String bio

	Date dateCreated
	Date lastUpdated

	static hasMany = [books: Book]

    static constraints = {
		firstName nullable: false, blank: false
		lastName nullable: false, blank: false
		bio nullable: true, maxSize: 1000
    }
}
