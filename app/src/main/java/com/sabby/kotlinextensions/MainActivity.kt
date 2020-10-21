package com.sabby.kotlinextensions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val imageUrl =
        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/10e1ce22-cd06-4069-972f-e5983cc638ee/d8sciq6-85f5e900-a187-45db-bc9a-d4d364242c01.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvMTBlMWNlMjItY2QwNi00MDY5LTk3MmYtZTU5ODNjYzYzOGVlXC9kOHNjaXE2LTg1ZjVlOTAwLWExODctNDVkYi1iYzlhLWQ0ZDM2NDI0MmMwMS5qcGcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.pybj7f4cZzYMPKlMZycBx6XSe2JbNOuIkkJv5dGpuxE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       // imageView.loadImage(imageUrl, R.mipmap.ic_launcher)
        imageView.loadImageWithOptions(imageUrl, GlideImageOptions(errorHolder = R.mipmap.ic_launcher,isCircular = true,scaleType = GlideScaleType.FitCenter))
    }
}