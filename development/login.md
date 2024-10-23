## 🔍 Login
소셜 로그인은 구글과 카카오에서 제공하는 API를 이용하여 구현했습니다.

이전 프로젝트 ARAD에서도 이와 같은 기능으로 구현되었기 떄문에 구현 내용은 [여기](https://github.com/Gnoam-R/ARAD/blob/main/project/functions/login.md)를 확인 바라며 dogdom에서는 ARAD 소셜 로그인과 다른점에 대하여 정리하고자 합니다.

## 📝 Feature Check List
  - [x] 소셜 로그인(카카오, 구글)
  - [x] Local DB 연결(Room)
  - [x] User information FireBase에 Upload

## 📷 Screenshot

<!-- 작업한 화면이 있다면 스크린 샷으로 첨부해주세요. -->

<h1 align="center">

|   upload to FireBase    |
| :-------------: |
| <img src="https://github.com/user-attachments/assets/a63353a0-0559-4837-913e-295340c3c175" width="500" height="450"/> |



</h1>

## 📮 관련 이슈

### 로그인, 파이어베이스, 로컬 DB Repository
LoginViewModel <-> LoginRepository <-> data(google, kakao)와 같은 구조를 가지고 있습니다.
Repository 디자인 패턴을 사용한 이유로는 리펙토링에 용의하고, 테스트 환경에 적합하다는 점이 있고 특히 코드 재사용에 있어서 제일 좋은 방식이라는 생각을 해서 입니다.

`GetCurrentUserProfile` 는 `resultLauncher`를 통해 실행되게 되며 로그인이 정상적으로 진행되게 되면 호출되는 메서드 입니다.
반환되느 정보로 아래 사용자 정보들이 콜백 파라미터 `userInfo` 로 전달하게 됩니다.

```kotlin
// GoogleLoginRepositoryImpl.kt
override fun GetCurrentUserProfile(upload: (GoogleInfo) -> Unit) {
    val curUser = com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(mContext)
    curUser?.let {
        val userInfo = GoogleInfo()
        userInfo.userId = curUser.id.toString()
        userInfo.email = curUser.email.toString()
        userInfo.name = curUser.displayName.toString()
        userInfo.role = UserRole.NORMAL
        userInfo.profileAddress = curUser.photoUrl.toString()
        upload(userInfo)
    }
}
```

아래 코드를 보게되면 `UserLocalDataSource` 로 부터 User에 대한 Local DB 인스턴스를 가져오며 `callBack을` 으로 전달되는 `userInfo` 를 앱의 Local DB, 파이어베이스에 전달하게 됩니다.

```kotlin
// LoginRepositoryImpl.kt
private fun googleLogin(user: UserLocalDataSource): Boolean {
    googleLoginRepository.signIn()
    googleLoginRepository.GetCurrentUserProfile{ userInfo ->
        if (isGetCurrentUserInfo()) {

        }
        else {
            user.addUser(UserEntity(
                name = userInfo.name,
                profile_image = userInfo.profileAddress,
                account_id = userInfo.userId,
            ))
            userRepository.uploadToServer(
                userInfo,
                path = "dogdom/user/user-google-${userInfo.userId}"
            )
        }
    }
    return true
}

```

LocalDB 데이터는 아래 쿼리문으로 가져올수 있으며 `Log`를 찍어 정상적으로 저장이 되었는지를 확인했습니다.
```kotlin
@Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): List<UserEntity>
```

![image](https://github.com/user-attachments/assets/874c757b-99c5-4c99-baa4-b60b8c58892b)

