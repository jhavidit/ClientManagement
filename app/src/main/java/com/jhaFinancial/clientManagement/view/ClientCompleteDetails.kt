package com.jhaFinancial.clientManagement.view

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.jhaFinancial.clientManagement.R
import com.jhaFinancial.clientManagement.databinding.FragmentClientCompleteDetailsBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class ClientCompleteDetails : Fragment() {
    private lateinit var binding: FragmentClientCompleteDetailsBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_client_complete_details,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.topAppBar.setNavigationOnClickListener {
            navController.popBackStack()
        }
        val name = arguments?.getString("name", "")
        val dob = arguments?.getString("dob", "")
        val fatherSpouse = arguments?.getString("father_spouse", "")
        val mother = arguments?.getString("mother")
        val maritalStatus = arguments?.getString("marital_status")
        val phone = arguments?.getString("phone")
        val email = arguments?.getString("email")
        val address = arguments?.getString("address")
        val pan = arguments?.getString("pan")
        val adhaar = arguments?.getString("adhaar")
        val occupation = arguments?.getString("occupation")
        val income = arguments?.getString("income")
        val investment = arguments?.getString("investment")
        binding.address.text = address
        binding.adhaarNumber.text = adhaar
        binding.clientName.text = name
        binding.dateOfBirth.text = dob
        binding.fatherName.text = fatherSpouse
        binding.maritalStatus.text = maritalStatus
        binding.motherName.text = mother
        binding.panNumber.text = pan
        binding.phoneNumber.text = phone
        binding.emailNumber.text = email
        binding.occupation.text = occupation
        binding.annualIncome.text = income
        binding.investmentInformationText.text = investment


            // START
            Dexter.withActivity(requireActivity())
                .withPermissions(
                    Manifest.permission.CALL_PHONE
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {


                    }


                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {

                        Snackbar.make(
                            binding.coordinatorLayout,
                            "Kindly allow call permission from setting",
                            Snackbar.LENGTH_LONG
                        ).setAction("go") {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", requireActivity().packageName, null)
                            intent.data = uri
                            startActivity(intent)
                        }.show()

                    }
                })
                .check()
            // END

        binding.callIcon.setOnClickListener {
            callUser(phone!!)
        }
        binding.emailIcon.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", email, null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text")
            requireContext().startActivity(Intent.createChooser(emailIntent, null))
        }
        binding.addressIcon.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:0,0?q=1600 $address")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }


    }

    private fun isCallEnabled(): Boolean {
        return (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CALL_PHONE
        )
                == PackageManager.PERMISSION_GRANTED
                )
    }

    private fun callUser(contact: String) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent =
                Intent(Intent.ACTION_CALL, Uri.parse("tel:${contact}"))
            startActivity(intent)
        } else {
            Snackbar.make(
                binding.coordinatorLayout,
                "Kindly allow call permission from setting",
                Snackbar.LENGTH_LONG
            ).setAction("go") {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
            }.show()
        }
    }



}