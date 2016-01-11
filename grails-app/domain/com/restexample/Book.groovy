package com.restexample

class Book {

	String title
	String isbn
	String description
	Date releaseDate

	Date dateCreated
	Date lastUpdated

	static belongsTo = Author
	static hasMany = [genres: Genre, authors: Author]

    static constraints = {
		title nullable: false, blank: false
		isbn nullable: false, blank:false
		description nullable: true, maxSize: 500
		releaseDate nullable: true
    }
}
