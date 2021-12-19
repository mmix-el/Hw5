package xyz.mmixel.hw5.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.mmixel.hw5.R
import xyz.mmixel.hw5.app.App
import xyz.mmixel.hw5.model.Contact

class MainActivity : AppCompatActivity(), ContactsFragment.ContactClickListener,
    ContactDetailsFragment.ButtonClickListener {
    private var isTablet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isTablet = resources.getBoolean(R.bool.isTablet)

        if (supportFragmentManager.findFragmentByTag(ContactsFragment.CONTACTS_TAG) == null) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.contactsContainer,
                    ContactsFragment.newInstance(),
                    ContactsFragment.CONTACTS_TAG
                )
                if (!isTablet) addToBackStack(ContactsFragment.CONTACTS_TAG)
                commit()
            }
        }
    }

    override fun onContactClicked(contact: Contact) {
        supportFragmentManager.beginTransaction().run {
            Bundle().putSerializable(ContactDetailsFragment.CONTACT_EXTRA, contact)
            if (!isTablet) {
                replace(
                    R.id.contactsContainer,
                    ContactDetailsFragment.newInstance(contact),
                    ContactDetailsFragment.CONTACT_DETAILS_TAG
                )
                addToBackStack(ContactDetailsFragment.CONTACT_DETAILS_TAG)
            } else {
                replace(
                    R.id.contactDetailsContainer,
                    ContactDetailsFragment.newInstance(contact),
                    ContactDetailsFragment.CONTACT_DETAILS_TAG
                )
            }
            commit()
        }
    }

    override fun onUpdateButtonClicked(contact: Contact) {
        (applicationContext as App).updateContact(contact)
        if (!isTablet) {
            supportFragmentManager.popBackStack()
        } else {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.contactsContainer,
                    ContactsFragment.newInstance(),
                    ContactsFragment.CONTACTS_TAG
                )
                commit()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1 || isTablet) finish()
        supportFragmentManager.popBackStack()
    }
}
