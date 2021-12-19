package xyz.mmixel.hw5.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import xyz.mmixel.hw5.R
import xyz.mmixel.hw5.app.App
import xyz.mmixel.hw5.databinding.FragmentContactsBinding
import xyz.mmixel.hw5.model.Contact

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private lateinit var binding: FragmentContactsBinding
    private lateinit var contacts: List<Contact>
    private var contactClickListener: ContactClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactClickListener = context as ContactClickListener
        contacts = (context.applicationContext as App).contacts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_contacts, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            listOf(person1, person2, person3, person4)
        }.onEachIndexed { i, it ->
            it.nameLabel.text = contacts[i].name
            it.surnameLabel.text = contacts[i].surname
            it.phoneLabel.text = contacts[i].phone

            it.root.setOnClickListener {
                contactClickListener?.onContactClicked(contacts[i])
            }
        }
    }

    interface ContactClickListener {
        fun onContactClicked(contact: Contact)
    }

    companion object {
        const val CONTACTS_TAG = "CONTACTS_TAG"
        fun newInstance() = ContactsFragment()
    }
}