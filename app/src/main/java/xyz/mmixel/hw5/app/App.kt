package xyz.mmixel.hw5.app

import android.app.Application
import xyz.mmixel.hw5.data.Datasource
import xyz.mmixel.hw5.model.Contact

class App : Application() {
    private lateinit var dataSource: Datasource

    override fun onCreate() {
        super.onCreate()
        dataSource = Datasource()
    }

    fun contacts(): List<Contact> {
        return dataSource.contacts
    }

    fun updateContact(contact: Contact) {
        dataSource.updateContact(contact)
    }
}