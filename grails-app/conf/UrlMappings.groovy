class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/api/authors"(resources: 'author')
		"/api/books"(resources: 'book')
		"/api/genres"(resources: 'genre')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
