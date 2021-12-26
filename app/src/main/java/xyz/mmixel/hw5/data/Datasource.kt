package xyz.mmixel.hw5.data

import xyz.mmixel.hw5.model.Contact

class Datasource {
    val contacts = listOf(
        Contact(
            0,
            "Jinny",
            "Dunphy",
            "+86 (525) 759-7368",
            0,
            "jdunphy0@dmoz.org"
        ),
        Contact(
            1,
            "Onofredo",
            "Spottiswoode",
            "+351 (855) 934-5968",
            1,
            "ospottiswoode1@arstechnica.com"
        ),
        Contact(
            2,
            "Calvin",
            "Pundy",
            "+54 (841) 947-9538",
            0,
            "cpundy2@instagram.com"
        ),
        Contact(
            3,
            "Humberto",
            "Bellie",
            "+502 (725) 782-8406",
            0,
            "hbellie3@tripod.com"
        )
    )

    fun updateContact(contact: Contact) {
        contacts.find { it.id == contact.id }?.apply {
            name = contact.name
            surname = contact.surname
            phone = contact.phone
            phoneType = contact.phoneType
            email = contact.email
        }
    }
}