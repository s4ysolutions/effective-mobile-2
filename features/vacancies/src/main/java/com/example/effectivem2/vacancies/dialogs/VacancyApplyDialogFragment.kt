package com.example.effectivem2.vacancies.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.transition.Visibility
import com.example.effectivem2.domain.models.Vacancy
import com.example.effectivem2.vacancies.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class VacancyApplyDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_vacancy_apply, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getSerializable("vacancy") as? Vacancy?)?.let { vacancy: Vacancy ->
            dialog?.findViewById<MaterialTextView>(R.id.title)?.let { textView ->
                textView.text = vacancy.title
            }
            dialog?.findViewById<View>(R.id.cl_layout)?.let { view ->
                view.visibility = GONE
            }
            dialog?.findViewById<MaterialButton>(R.id.add_cl)?.let { button ->
                button.visibility = VISIBLE
                button.setOnClickListener {
                    button.visibility = GONE
                    dialog?.findViewById<View>(R.id.cl_layout)?.let { view ->
                        view.visibility = VISIBLE
                    }
                }
            }
        }

    }

    companion object {
        private val TAG = VacancyApplyDialogFragment::class.java.simpleName
    }
}