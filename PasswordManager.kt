import java.util.Scanner

// Data class to store individual login details
data class Credential(val website: String, val username: String, var password: String)

class PasswordManager {
    // Mutable list to store all saved credentials
    private val credentials = mutableListOf<Credential>()

    // Function to add a new credential
    fun addCredential(scanner: Scanner) {
        print("Enter website: ")
        val site = scanner.nextLine()

        print("Enter username: ")
        val user = scanner.nextLine()

        print("Enter password: ")
        val pass = scanner.nextLine()

        // Add the new credential to the list
        credentials.add(Credential(site, user, pass))
        println("Credential saved.\n")
    }

    // Function to display all saved credentials
    fun viewCredentials() {
        if (credentials.isEmpty()) {
            println(" No credentials saved.\n")
            return
        }

        println("\nSaved Credentials:")
        // Loop through the list and print each credential
        credentials.forEachIndexed { index, cred ->
            println("${index + 1}. Website: ${cred.website}, Username: ${cred.username}, Password: ${cred.password}")
        }
        println()
    }

    // Function to search credentials by website
    fun searchCredential(scanner: Scanner) {
        print("Enter website to search: ")
        val query = scanner.nextLine()

        // Filter credentials that match the search term
        val result = credentials.filter { it.website.contains(query, ignoreCase = true) }

        if (result.isEmpty()) {
            println("No matching credentials found.\n")
        } else {
            println("\nSearch Results:")
            result.forEach {
                println("Website: ${it.website}, Username: ${it.username}, Password: ${it.password}")
            }
            println()
        }
    }

    // Function to delete a credential by index
    fun deleteCredential(scanner: Scanner) {
        // Show all current credentials
        viewCredentials()
        if (credentials.isEmpty()) return

        print("Enter the number of the credential to delete: ")
        val input = scanner.nextLine().toIntOrNull()

        // Check if user input is valid and delete the selected item
        if (input != null && input in 1..credentials.size) {
            credentials.removeAt(input - 1)
            println("Credential deleted.\n")
        } else {
            println("Invalid selection.\n")
        }
    }
}

// Main function: the starting point of the app
fun main() {
    val scanner = Scanner(System.`in`)
    val manager = PasswordManager()

    // Infinite loop for menu until user exits
    while (true) {
        // Print menu options
        println(
            """
            ==== Password Manager ====
            1. Add Credential
            2. View All Credentials
            3. Search Credential
            4. Delete Credential
            5. Exit
            Choose an option:
        """.trimIndent()
        )

        // Handle user's menu choice using when
        when (scanner.nextLine()) {
            "1" -> manager.addCredential(scanner)
            "2" -> manager.viewCredentials()
            "3" -> manager.searchCredential(scanner)
            "4" -> manager.deleteCredential(scanner)
            "5" -> {
                println("Exiting Password Manager. Goodbye!")
                break  // Exit the loop and end the program
            }
            else -> println("Invalid option, please try again.\n")
        }
    }
}
