import com.restexample.Author
import com.restexample.Book
import com.restexample.Genre
import com.restexample.security.Role
import com.restexample.security.User
import com.restexample.security.UserRole
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
		// Configure Custom ObjectMarshallers
		JSON.registerObjectMarshaller(Book) {
			def map = [:]
			map['id'] = it.id
			map['title'] = it.title
			map['isbn'] = it.isbn
			map['authors'] = it.authors
			map['desc'] = it.description
			return map
		}

		JSON.registerObjectMarshaller(Author) {
			def map = [:]
			map['id'] = it.id
			map['displayName'] = "$it.lastName, $it.firstName"
			map['lastName'] = it.lastName
			map['firstName'] = it.firstName
			map['bio'] = it.bio
			return map
		}

		JSON.registerObjectMarshaller(Genre) {
			def map = [:]
			map['id'] = it.id
			map['name'] = it.name
			return map
		}


		// Define a couple of users.
		def admin = new User(username: 'admin', password: 'password').save(flush:true, failOnError: true)
		def user = new User(username: 'user', password: 'password').save(flush:true, failOnError: true)

		// Define a couple of roles.
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true, failOnError: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush:true, failOnError: true)

		// Add users to Role
		UserRole.create(admin, adminRole, true)
		UserRole.create(user, userRole, true)


		// Add a couple of Genres
		def history = new Genre(name: 'History').save(flush: true, failOnError: true)
		def sciFi = new Genre(name: 'Science Fiction').save(flush: true, failOnError: true)
		def suspense = new Genre(name: 'Suspense').save(flush: true, failOnError: true)
		def tech = new Genre(name: 'Technology').save(flush: true, failOnError: true)

		// Add some Authors

		def gaiman = new Author(firstName: 'Neil', lastName: 'Gaiman').save(flush:true, failOnError: true)
		def kenyon = new Author(firstName: 'F.W.', lastName: 'Kenyon').save(flush:true, failOnError: true)
		def mcleod = new Author(firstName: 'Cynthia', lastName: 'McLeod').save(flush:true, failOnError: true)

		def king = new Author(firstName: 'Stephen', lastName: 'King', bio: '(born September 21, 1947) is an American author of contemporary horror, supernatural fiction, suspense, science fiction, and fantasy. His books have sold more than 350 million copies,[2] many of which have been adapted into feature films, miniseries, television shows, and comic books. King has published 54 novels, including seven under the pen name Richard Bachman, and six non-fiction books. He has written nearly 200 short stories, most of which have been collected in book collections. Many of his stories are set in his home state of Maine. His novella "Rita Hayworth and Shawshank Redemption" was the basis for the movie "The Shawshank Redemption" which is widely regarded one of the greatest films of all time.').save(flush:true, failOnError: true)

		def carrie = new Book(title: 'Carrie', isbn: '978-0-385-08695-0', releaseDate: new Date().parse('MM/dd/yyyy','04/05/2974'))
		carrie.addToGenres(suspense)
		def shining =new Book(title: 'The Shining', isbn: '978-0-385-12167-5', releaseDate: new Date().parse('MM/dd/yyyy', '01/28/1977'))
		carrie.addToGenres(suspense)
		def jfk = new Book(title: '11/22/63', isbn: '978-1-4516-2728-2', releaseDate: new Date().parse('MM/dd/yyyy', '11/8/2011'))
		carrie.addToGenres(suspense)
		carrie.addToGenres(history)

		king.addToBooks(carrie)
		king.addToBooks(shining)
		king.addToBooks(jfk)
		king.save()

		def crichton = new Author(firstName: 'Michael', lastName: 'Crichton', bio: '(October 23, 1942 – November 4, 2008) was an American best-selling author, physician, producer, director and screenwriter, best known for his work in the science fiction, medical fiction and thriller genres. His books have sold over 200 million copies worldwide, and many have been adapted into films. In 1994, Crichton became the only creative artist ever to have worked simultaneously charting at No. 1 in US television (ER), film (Jurassic Park), and book sales (Disclosure).[1]').save(flush: true, failOnError: true)

		def jp = new Book(title: 'Jurassic Park', isbn: '0-394-58816-9', releaseDate: new Date().parse('MM/dd/yyyy', '11/01/1990'))
		def tlw = new Book(title: 'The Lost World', isbn: '0-345-40288-X', releaseDate: new Date().parse('MM/dd/yyyy', '09/01/1995'))
		jp.addToGenres(sciFi)
		jp.addToGenres(suspense)

		tlw.addToGenres(sciFi)
		tlw.addToGenres(suspense)

		crichton.addToBooks(jp)
		crichton.addToBooks(tlw)
		crichton.save()

	}
    def destroy = {
    }
}
