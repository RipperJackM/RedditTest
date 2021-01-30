package com.example.reddittest.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reddittest.databinding.MediaDetailViewBinding
import kotlinx.android.synthetic.main.media_detail_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


/* don't worry about all this shit. it's just my mistake when i decide
    to use view instead of activity to manage images
     */

class MediaDetailView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: MediaDetailViewBinding =
            MediaDetailViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var imageUrl: String? = ""
    private var activity: Activity? = null
    private var updateBtn: Button? = null

    init {
        binding.mediaDetailContainer.setOnClickListener {
            binding.mediaDetailContainer.visibility = View.GONE
            updateBtn?.visibility = View.VISIBLE
        }

        binding.closeBtn.setOnClickListener {
            binding.mediaDetailContainer.visibility = View.GONE
            updateBtn?.visibility = View.VISIBLE
        }

        binding.saveBtn.setOnClickListener(SaveImageClickListener())
    }

    inner class SaveImageClickListener : OnClickListener {
        override fun onClick(v: View?) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity!!,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                        101)
            } else saveImageToGallery()
        }
    }

    fun showMediaDetail(activity: Activity, url: String?, button: Button) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(binding.image)

        this.activity = activity
        imageUrl = url
        updateBtn = button

        showView()
    }

    private fun showView() {
        binding.mediaDetailContainer.visibility = View.VISIBLE
    }

    fun saveImageToGallery() {
        GlobalScope.launch(Dispatchers.IO) {
            saveImageInStore(Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .submit()
                    .get())
        }
    }

    // New images will overwrite old ones, because have the same fileName
    private fun saveImageInStore(image: Bitmap): String? {
        var savedImagePath: String? = null
        val imageFileName = "Picture" + ".jpg"
        val storageDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + " Reddit"
        )
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.absolutePath
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            galleryAddPic(savedImagePath)
        }
        return savedImagePath
    }

    private fun galleryAddPic(imagePath: String?) {
        imagePath?.let { path ->
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(path)
            val contentUri: Uri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            context.sendBroadcast(mediaScanIntent)
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(context, "Image was saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}