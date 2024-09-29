## 🔍 MVVM이란?

Model - View - ViewModel 이 각각의 역할을 분리하여 가독성과 재사용성을 높인 디자인 패턴을 말한다.

저는 앞서 MVC 패턴의 프로젝트를 진행 했었고 findViewByID, viewBinding, callBack, Listener 등등을 통해 
View의 상태 변화를 실시간으로 입력받아 업데이트 해봤기 때문에 MVVM, dataBinding, LiveData를 사용해 이 프로젝트를 진행하고자 합니다.

### 사용 이유?
큰 이유는 없고 이러한 작은 프로젝트를 진행함에 있어 MVC로 개발하는것이 훨씬 효율적이다 라고 할 수 있지만 MVVM의 장점인 유지보수, DataBinding과 LiveData의 호완성, Hilt(의존성 적용) 등등 코드를 구현함에 있어 여러 장점들이 존재하기 때문에 

## 🔍 MVC와 어떤 차이가 있는가?

iOS와 Android는 마찬가지로 기본적인 설정으로 MVC를 채택하는 것으로 알고있는데 현재는 MVVM 디자인 패턴을 주로 사용하는것 같음

MVC 패턴에 대한 정의로는 Controller라는 것의 역할이 많은 양을 차지하고 있고 그에 따라서 Model과 View에 부담은 줄어들지만 Controller에 의존성이 몽땅 들어가 있는 디자인 패턴이라고 생각하면 될 것으로 생각된다.

iOS는 ViewController 그리고 안드에서는 Activity 또는 Fragment가 Controller 입니다.

## 🔍 DataBinding과 LiveData 

저는 DataBinding라는 Jetpack 라이브러리를 dogdom의 프로젝트에 적용하게 되었는데
위 방법은 프로그래밍의 방식이라기 보다는 선언적 형식을 사용하여 XML 레이아웃의 UI 구성요소를 앱의 테이터 소스로 바인딩하는 방법입니다.

위 [Google Developer Guide](https://developer.android.com/topic/libraries/data-binding?hl=ko) 내용을 기반으로 이 프로젝트에 적용했습니다.

### xml dataBinding 설정
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    >
    <data>
        <variable
            name="vm"
            type="com.roh.dogdom.views.release.ReleaseDynamicViewModel" />
    </data>
    .
    .
    .
</layout>
```

### ViewModel.kt
``` kotlin
class ReleaseDynamicViewModel () : ViewModel(){
    ...
    private val _loadImage = SingleLiveEvent<Unit>()
    val loadImage: LiveData<Unit> get() = _loadImage
    ...
    fun loadImage(num: Int) {
        ...
    }
}
```

### xml
```xml
<androidx.appcompat.widget.AppCompatImageButton
    android:id="@+id/iv_release_content1"
    ...
    android:onClick="@{() -> vm.loadImage(1) }"
    ...
    />

```

위 내용을 간단요약 하자면 Data Binding에 가장 중요한 특징 Observable을 사용 할 수 있기 때문인데 뷰의 상태를 추적할 수 있는 기능이기 때문에 앱의 View가 데이터 (상태)의 변화에 따라 자동으로 변경될 수 있다.
저는 LiveData로 해당 프로젝트를 진행하게 되었고 `button` 과 `EditText`에 LiveData와 DataBinding을 적용해봤습니다

## 📮 MVVM 적용
...

## 결론
나는 이 DataBinding을 활용하여 ViewModel을 Activity(혹은 Fragment)에 연결할 생각이었고 구현은 위 방식을 기반으로 xml, class 파일 등에 구현하게 되었습니다.


### 참고:
[Google Developer](https://developer.android.com/topic/libraries/data-binding?hl=ko)

[블로그](https://medium.com/androiddevelopers/android-data-binding-library-from-observable-fields-to-livedata-in-two-steps-690a384218f2)
