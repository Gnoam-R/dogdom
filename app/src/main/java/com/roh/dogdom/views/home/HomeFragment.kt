package com.roh.dogdom.views.home


import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.roh.dogdom.R
import com.roh.dogdom.api.*
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.data.db.BaseDb
import com.roh.dogdom.data.db.BaseLocalDataSource
import com.roh.dogdom.data.db.Log.LoggerLocalDataSource
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.FragmentHomeBinding
import com.roh.dogdom.navigator.AppNavigator
import com.roh.dogdom.util.enumUiColorPos
import com.roh.dogdom.views.log.ButtonsFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){

    // retrofit2 test
    private lateinit var retrofitService: RetrofitService
    private lateinit var retrofit : Retrofit

    @Inject lateinit var baseDb: BaseLocalDataSource


    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var homeAdapter : HomeAdapter
    private lateinit var mainPost : MainPost

    lateinit var messages: MutableList<ChatMessage>

    private val viewModel by viewModels<HomeViewModel>()

    private val homeContentsFirstFragment = HomeContentsFirstFragment()
    private val homeContentsSecondFragment = HomeContentsSecondFragment()
    private val buttonsFragment = ButtonsFragment()

   lateinit var fbDatabase : FirebaseDatabase
   lateinit var fbDatabaseRef : DatabaseReference
   lateinit var fbStorage : FirebaseStorage

   public fun provideContext() : Context {
       return mContext
   }

   private fun upLoadFB() {
       fbStorage = FirebaseStorage.getInstance()
       // Create a storage reference from our app
       val storageRef = fbStorage.reference
       // Create a reference to "mountains.jpg"
       val mountainsRef = storageRef.child("mountains.jpg")
       // Create a reference to 'images/mountains.jpg'
       val mountainImagesRef = storageRef.child("0604_file/mountains.jpg")
       // While the file names are the same, the references point to different files
       mountainsRef.name == mountainImagesRef.name // true
       mountainsRef.path == mountainImagesRef.path // false
   }
    private fun downLoadFB() {
        // Create a storage reference from our app
        val storageRef = fbStorage.reference

        // Create a reference with an initial file path and name
        val pathReference = storageRef.child("0604_file/0604_image1")

        // Create a reference to a file from a Google Cloud Storage URI
        val gsReference = fbStorage.getReferenceFromUrl("gs://bucket/images/stars.jpg")

        // Create a reference from an HTTPS URL
        // Note that in the URL, characters are URL escaped!
        val httpsReference = fbStorage.getReferenceFromUrl(
            "https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg",
        )
    }

    private fun initFB() {
        fbDatabase = Firebase.database
        fbDatabaseRef = fbDatabase.getReference("roh/test")
        fbDatabaseRef.setValue("Hello, World!")
        fbDatabaseRef = fbDatabase.getReference("roh/test2")
        fbDatabaseRef.setValue("goodbye, world!")
        fbDatabaseRef.setValue("safsdfa")
        fbDatabaseRef = fbDatabase.getReference("roh/test3")
//        upLoadFB()

//        fbStorage.reference.child("0604_file").downloadUrl.addOnSuccessListener {
//            Log.e("HomeFragment", "fbStorage : ${it}")
//        }
    }
    private fun initDB(aa : Int) {
        Log.e("initDB ", "${aa}")
        baseDb.addLog("test : ${aa}" )
        baseDb.getAllLogs { it ->
            Log.e("initDB ", "${it}")
        }

    }

    var aa = 0

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        clearSystemStatusBarLayout()
        replaceFragment(1, homeContentsFirstFragment)
        replaceFragment(2, homeContentsSecondFragment)
        initViewModelCallback()
        initRetrofit()
        initFB()

        Log.e("HomeFragment","${findNavController().currentDestination?.id}")

        binding.btTgAlarm.setOnClickListener {
            Log.e("HomeFragment","${aa}")
            initDB(aa++)
//            replaceFragment(3, buttonsFragment)
//            navigator.navigateTo(Screens.BUTTONS)
        }
        binding.transformationLayout.setOnClickListener {
            binding.transformationLayout.startTransform()
        }
        binding.btTgDiscover.setOnClickListener {
            baseDb.removeLogs()
        }
        val etQuestion = binding.etSearch
    }

/*    private fun getSearchList(question: String) {
        Log.e("HomeFragment", "getSearchList")
        retrofitService.getChatCompletion(
            requestBody = ChatGptRequest(
                model = "gpt-3.5-turbo",
                messages = listOf(ChatMessage("user", "$question")),
                temperature = 1,
                max_tokens = 512,
                top_p = 1,
                frequency_penalty = 0,
                presence_penalty = 0
            )
        ).enqueue(object : Callback<ChatGptResponse> {
            override fun onResponse(
                call: retrofit2.Call<ChatGptResponse>,
                response: retrofit2.Response<ChatGptResponse>
            ) {
                val message = response.body()?.choices?.get(0)?.message?.content
                Log.e("HomeFragment", "response : ${response}")
            }

            override fun onFailure(call: retrofit2.Call<ChatGptResponse>, t: Throwable) {
                Log.e("HomeFragment", "t : $t")
            }

        })
    }
*/
    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    private fun replaceFragment(fragmentNum : Int, fragment: Fragment) {
        // 현 Activity 에 연결된 Fragment 관리하는 supportFragmentManager 를 통해 Fragment 전환
        when(fragmentNum){
            1 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container1, fragment)
                    commit()
                }
            }
            2 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container2, fragment)
                    commit()
                }
            }
            3 -> {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.main_fragment_container2, fragment)
                    commit()
                }
            }
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goEx.observe(viewLifecycleOwner, Observer {
                Log.e("MasterMainViewModel", "goEx")
                goEx()
            })
        }
    }
}

