package ooftf.com.widget.self

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import ooftf.com.widget.R


/**
 *  删除按钮的大小为40dp
 *  隐藏转换按钮的大小为38dp
 * Created by master on 2017/10/17 0017.
 */
class OperationEditTextLayout : RelativeLayout {
    constructor(context: Context?) : super(context) {
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        obtainAttrs(attrs)
    }


    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        obtainAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        obtainAttrs(attrs)
    }

    private var iconShowId = R.drawable.vector_drawable_attention_fill
    private var iconHideId = R.drawable.vector_drawable_attention_forbid_fill
    private var iconDelId = R.drawable.vector_icon_del
    private var maskOperationEnabled = false
    private var delOperationEnabled = false
    private var editTextId = -1;
    private lateinit var editText: EditText
    private fun obtainAttrs(attrs: AttributeSet) {
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.OperationEditText)
        iconShowId = attrsArray.getResourceId(R.styleable.OperationEditText_icon_show, R.drawable.vector_drawable_attention_fill)
        iconHideId = attrsArray.getResourceId(R.styleable.OperationEditText_icon_hide, R.drawable.vector_drawable_attention_forbid_fill)
        iconDelId = attrsArray.getResourceId(R.styleable.OperationEditText_icon_del, R.drawable.vector_icon_del)
        maskOperationEnabled = attrsArray.getBoolean(R.styleable.OperationEditText_maskOperationEnabled, false)
        delOperationEnabled = attrsArray.getBoolean(R.styleable.OperationEditText_delOperationEnabled, false)
        editTextId = attrsArray.getResourceId(R.styleable.OperationEditText_editTextId, -1)
        attrsArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if(editTextId != -1){
            editText = findViewById(editTextId)
        }else if(getChildAt(0) is EditText){
            editText = getChildAt(0) as EditText
        }else{
            throw IllegalAccessException("OperationEditTextLayout 未找到EditText节点")
        }
        initViews()
        addListener()
    }

    private fun addListener() {
        delView.setOnClickListener {
            editText.text.clear()
        }
        maskView.setOnClickListener {
            toggleMaskState()
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {
                    showMaskOperation()
                    showDelOperation()
                } else {
                    hideMaskOperation()
                    hideDelOperation()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun toggleMaskState() {
        if (maskPassword) {
            unmaskPassword()
        } else {
            maskPassword()
        }
    }

    fun showMaskOperation() {
        if (maskOperationEnabled) {
            maskView.visibility = View.VISIBLE
        } else {
            maskView.visibility = View.GONE
        }
        calculatePadding()
    }

    private fun calculatePadding() {
       /* if (delView.left > 0) {
            editText.setPadding(editText.paddingLeft, editText.paddingTop, editText.width - delView.left, editText.paddingBottom)
        }*/
    }

    fun hideMaskOperation() {
        maskView.visibility = View.GONE
        calculatePadding()
    }

    fun showDelOperation() {
        if (delOperationEnabled) {
            delView.visibility = View.VISIBLE
        } else {
            delView.visibility = View.GONE
        }
        calculatePadding()
    }

    fun hideDelOperation() {
        delView.visibility = View.GONE
        calculatePadding()
    }


    lateinit var delView: ImageView
    lateinit var maskView: ImageView
    var maskPassword = true
    private fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.layout_edit_operation, this)
        delView = findViewById(R.id.del)
        maskView = findViewById(R.id.mask)
        delView.setImageResource(iconDelId)
        maskView.setImageResource(iconHideId)
        visibleControl()
        gravity = Gravity.CENTER_VERTICAL
        maskPassword = editText.transformationMethod != null
        maskPassword()
    }

    private fun visibleControl() {
        if (editText.text.isNotEmpty()) {
            showMaskOperation()
            showDelOperation()
        } else {
            hideMaskOperation()
            hideDelOperation()
        }
    }

    private fun maskPassword() {
        if (!maskOperationEnabled) return
        maskPassword = true
        maskView.setImageResource(iconHideId)
        var selection = editText.selectionStart
        editText.transformationMethod = PasswordTransformationMethod.getInstance()
        editText.setSelection(selection)
    }

    private fun unmaskPassword() {
        if (!maskOperationEnabled) return
        maskPassword = false
        maskView.setImageResource(iconShowId)
        var selection = editText.selectionStart
        editText.transformationMethod = null
        editText.setSelection(selection)
    }
}