package xyz.mmixel.hw5.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import xyz.mmixel.hw5.R
import xyz.mmixel.hw5.databinding.FragmentContactDetailsBinding
import xyz.mmixel.hw5.model.Contact

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {
    private lateinit var binding: FragmentContactDetailsBinding
    private var buttonClickListener: ButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        buttonClickListener = context as ButtonClickListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_details, container, false)
        return binding.root
    }

    private var contactId = -1
    private var contactPhoneType = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contact: Contact = requireArguments().getSerializable(CONTACT_EXTRA) as Contact
        with(binding) {
            contactId = contact.id
            nameEditText.setText(contact.name)
            surnameEditText.setText(contact.surname)
            phoneEditText.setText(contact.phone)
            contactPhoneType = contact.phoneType
            emailEditText.setText(contact.email)

            updateButton.setOnClickListener {
                buttonClickListener?.onUpdateButtonClicked(
                    Contact(
                        contactId,
                        nameEditText.text.toString(),
                        surnameEditText.text.toString(),
                        phoneEditText.text.toString(),
                        contactPhoneType,
                        emailEditText.text.toString()
                    )
                )
            }
        }

    }

    interface ButtonClickListener {
        fun onUpdateButtonClicked(contact: Contact)
    }

    companion object {
        const val CONTACT_DETAILS_TAG = "CONTACT_DETAILS_FRAGMENT_TAG"
        const val CONTACT_EXTRA = "CONTACT_EXTRA"

        fun newInstance(contact: Contact) = ContactDetailsFragment().also {
            it.arguments = Bundle().apply {
                putSerializable(CONTACT_EXTRA, contact)
            }
        }
    }
}