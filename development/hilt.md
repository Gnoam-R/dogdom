## 🔍 hilt

### 의존성
간단하게 위존성을 주입하는 것에 대한 이유 & 목적 정도만을 설명하자면, 객체 지향 프로그래밍에서 내부에서 객체를 생성하는 것과 외부에서 생성후 주입 받는것은 큰 차이가 있다.

```kotlin
class Engine

// car는 engine에 의존한다
class Car {
  val engine = Engine()
}

================================

class Car (val engine: Engine) {

}

```

외부에서 생성자를 통해 객체를 의존 관계를 주입받게 되는 경우 어떠한 장점이 있을까?

- Car는 Engine 생성에 대한 책임이 없다
- Engine의 코드가 변경되더라도 Car의 코드에는 변함이 없다
- 즉 결합도를 낮추고 재사용성을 높이게 됩니다

### Dagger와 hilt란
안드로이드를 개발하는 구글에서 개발한 의존성 라이브러리들이고 컴파일 타임에 의존성을 부여하기 때문에 높은 성능을 제공합니다.
'hilt'는 Dagger의 복잡성을 줄이고 안드로이드의 생명주기를 고려하여 만들어져 애플리케이션에 최적화된 설정으로 사용이 가능합니다.


## 📝 Feature Check List
  - [x] 의존성 학습
  - [x] hilt 적용
  - [ ] MVVM & 클린 아키택처 적용

## 📷 Screenshot

<h1 align="center">

|   Login View    |
| :-------------: |
| <img src="https://github.com/user-attachments/assets/c884c4fe-fb5a-4b2d-b0d6-9a1ef62251b1" width="220" height="450"/> |

</h1>

## 📮 관련 이슈
### 의존성 적용

의존성 주입하는 방법에 대한 설명은 [여기](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko)를 확인하고, 의존성 주입에 대한 몇가지 생각들을 정리하고자 한다.

결론 부터 말하자면 의존성 주입은 객체 지향 프로그래밍의 단일책임을 준수해야 된다고 생각합니다. 
이유는 아까 위에서 의존성을 적용하는 이유에 대해서 설명했드시 의존성 주입은 결합도를 낮추고 재사용성을 높혀야 되는 것인데 이는 객체지향 프로그래밍의 방식(SOLID)를 지켜야하며 안드로이드에서 이러한 방법을 최대한 지키는 아키택처는 `MVVM + 클린 아키택처` 방식이라고 생각합니다.

따라서 아래와 같이 코드를 설계했다는 점을 말씀드립니다.

<img src="https://github.com/user-attachments/assets/9b53a14a-1254-4451-864f-17be8880c62b" width="900" height="500"/>

### 로그인 화면에 로그인 관련 기능(Social Login, User DB) 의존성 주입

``` kotlin
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository

) : ViewModel(){
```

`@Inject` 키워드를 사용해 LoginViewModel의 생성자로써 의존성을 주입하게 되었습니다.
위 키워드는 Dagger나 hilt에서 사용되는 라이브러리의 어노테이션 키워드로 의존성을 주입할때 사용되며 객체에 대한 의존성을 자동으로(컴파일 타임에) 주입하게 됩니다.

또한 의존성 주입은 크게 2가지 방식으로 구성되어 있는데 
1. 생성자로 의존성을 주입하는 방법
2. 필드(맴버 변수)로 의존성을 주입하는 방법

위 2가지 중 저는 첫번째인 생성자로 의존성을 주입하는 방법을 선택하였는데 그 이유로는 하위 로그인 구성 레포지터리가 우선적으로 의존성이 처리되어야 하기 때문으로 클래스간의 의존관계를 보다 명확하게 확인할 수 있는 장점이 있기 때문에 이와 같이 구현하게 되었습니다.

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Singleton
    @Provides
    fun provideLoginRepository(
    ) : LoginRepository {
        return LoginRepositoryImpl(
            GoogleLoginRepositoryImpl(),
            KakaoLoginRepositoryImpl(),
            UserRepositoryImpl(),
        )
    }
}
```

