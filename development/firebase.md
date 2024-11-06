## 🔍 파이어베이스
### 파이어베이스 사용한 이유
파이어베이스는 서버리스 애플리케이션 개발을 지원하는 플랫폼이기 때문에 일정수의 사용량(유저수)를 넘어서지 않는 경우에는 무료로 해당 API를 활용할 수 있습니다.
또한 저는 총 2가지 기능을 구현해야 되는데

1. 사용자 정보, 이미지 데이터 업로드, 다운로드
2. FCM

첫번째는 사용자의 로그인 정보와 Post 업로드(이미지, 시간, 제목, 내용 등)을 업로드 & 다운하기 위해서 사용되어야 하는 기능
두번쨰는 타 유저의 SNS Posting을 알리기 위해서 사용되어야 하는 기능

그렇기 때문에 FireBase API를 사용하게 되었습니다.

파이어베이스에는 두 종류의 데이터베이스가 존재합니다. 

하나는 `cloud firebase`, 나머지는 `real time database` 가 있는데 저의 경우 image와 같은 고용량의 데이터를 빠른 동기화를 통해 사용자에게 제공해줘야 하는 플랫폼을 개발 하고 있기 때문에 `real time database`를 선택하게 되었습니다.

이러한 API를 사용하기 위해서 다양한 사이트에서 확인이 가능했기 때문에 구현 방법에 대한 내용 보다는 구현시 어떤 고려 사항이 있었는지를 정리하고자 합니다.

## 📝 Feature Check List
  - [x] Real time database 연동
  - [x] 추상화
  - [ ] 성능 최적화

## 📷 Screenshot

<h1 align="center">

|   사용자 정보 업로드   |  이미지 로드   |
| :-------------: | :-------------: |
| <img src="https://github.com/user-attachments/assets/a63353a0-0559-4837-913e-295340c3c175" width="400" height="450"/> | <img src="https://github.com/user-attachments/assets/3f42ddb8-cba1-4ce4-8331-639dfb0f496f" width="500" height="450"/>

</h1>

## 📮 관련 이슈
### 오버 로딩

소셜 로그인 정보와 사용자의 포스팅 정보를 업로드 할때 초기에 매개변수를 활용했지만 직관적이지 못한 코드 구성과 코드의 안전성을 위해서 오버 로딩을 선택하게 되었습니다.

```kotlin
override fun uploadToServer(userInfo : UserInfo, path: String) {
    fbDatabaseRef = fbDatabase.getReference(path)
    fbDatabaseRef.setValue(userInfo)
}
override fun uploadToServer(userInfo : GoogleInfo, path: String) {
    fbDatabaseRef = fbDatabase.getReference(path)
    fbDatabaseRef.setValue(userInfo)
}
```

```kotlin
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
            userInfo.role = UserRole.ADMIN
            userRepository.uploadToServer(
                userInfo,
                path = "dogdom/user/user-google-${userInfo.userId}"
            )

            user.getAllUsers {
                Log.e("LoginRepositoryImpl", "$it")
            }
        }
    }
    return true
}
```

|  RecyclerView 이미지 로드   |
| :-------------: |

https://github.com/user-attachments/assets/d4f1ecb4-15dd-4c9d-b984-df9247ae0219


