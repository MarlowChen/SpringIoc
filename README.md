# SpringIoc

# 說明
Spring的依賴注入目的是降低耦合度
讓Spring去實作物件，不需要new出物件，這也是Spring主要的核心功能。


Spring的注入在常見的教學通常寫得非常複雜，因為注入方式很靈活
網上文件也寫得非常複雜

這個文件主要是以最簡單、最基礎的方式
講述Spring的控制反轉與依賴注入。
* * *
# 方法

Spring的依賴注入依據官方的說明，有三個方法
但實際上只有兩種，因為接口注入Spring並不支援。

<ol>
<li>interface injection : Spring不支援，原因很多，而且類似Setter注入。</li>
<li>Setter Injection : 定義Java文件的一個名為Set的方法，Spring可以判斷並且注入。</li>
<li>Constructor Injection : 不需要 Set，透過構造注入</li>
</ol>
***
# 配置

Spring的Ioc基礎需要的檔案如下：

<ul>
<li>SpringFrameWork : Spring的Jar檔。</li>
<li>Beans.xml：注入物件，可供Spring解讀，會直接完成物件的實作。</li>
<li>Object：需要注入的物件，可供Spring編譯成一個物件實體。</li>
<li>GetBean：獲取物件實體的方式，一般會寫測試方法。</li>
</ul>

只要使用Spring，就一定會有上述的方式，只是使用上的差異而已。
* * *
# 步驟

<img src="images/Untitled Diagram.jpg" >

<ol>
<li>首先載入Spring相關的jar，讓文件可以使用Spring框架。</li>
<li>定義需要的物件，並且依據Spring的規則進行操作
<ul>
<li>Setter Injection : 在物件中建立Set方法</li>
<li>Constructor Injection : 在物件中加入建構子並給予變數接收</li>
</ul></li>
<li>Object：需要注入的物件，可供Spring編譯成一個物件實體。
<ul>
<li>Setter Injection : 使用property注入值</li>
<li>Constructor Injection : 使用constructor-arg注入值</li>
</ul></li>
<li>GetBean：獲取物件實體的方式，一般會寫測試方法。</li>
</ol>

在上述兩種方法的差異，只有在定義Bean.xml與Object不同而已
其餘方式都可以使用。
* * *
這邊使用maven，所以運用pom.xml載入SpringFramework
範例載入的是個人資料，包含String與int型別

## pom.xml:載入SpringFramework
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.3.6.RELEASE</version>
		</dependency>
## 定義所需要物件(這邊其中一種即可)
    
### ---Setter(定義物件):運用set方法來注入
#### ----用法： 

      public void setXXX(key){
        //get key instance
      }
  
說明：內容主要是使用Set方式來載入想要的東西，只要將想要的物件透過set方式，參數帶物件，就可以載入
在Setter中的PersonalInfo.java當中為：

#### ----實例：

		private String name;
		
		public void setName(String name){
			this.name = name;
		}
		public String getName(){
			return name;
		}	
    
說明： 用一個全域變數name，在把name交給Spring帶值傳進去，就可以注入，get則是可以透過這個方法取值
這裡很重要的是，set方式一定要依照setXXX並且放入需要的物件才會注入。

### ---Constructor(定義物件):直接定義建構子
#### ----用法： 

    public Object(key){
      //get key instance
    }	
  
說明：也就是這裡完全不需要Set方式，直接請Spring完成，使用者可以自定義取得方法。

#### ----實例：

    private String name;
    
    public PersonalInfo(String name){
      this.name = name;
    }
    
說明：類別名稱為PersonalInfo，所以用這個建構子就可以直接把name注入。

* * *

## 定義Bean的xml(對應上面的方法)

### ---Setter(定義beans.xml):使用property注入
#### ----用法：
       <bean id="objectID" class="classPackage">
           <property name="key" value="value"/>
       </bean>
   
說明：使用<bean>的tag並且給id，id就是new出來的自訂物件名稱，
class是這個物件所在的package，property是對應setter的方式，
name的名稱對應到前面的setXXX，XXX就是name的名稱，
Spring會從這裡給值到剛set當中的參數作為使用。

#### ----實例：

     <bean id="personalInfo" class="org.Spring.SetterInjection.PersonalInfo">
         <property name="name" value="Daniel"/>
     </bean>
    
說明：前面setName，所以這邊也使用name(不必區分大小寫)，然後給予值，
class是這個物件的位置，id是拿取這個物件需要使用的。

* * *

### ---Constructor(定義beans.xml):使用constructor-arg注入
#### ----用法：
     <bean id="objectID" class="classPackage">
         <constructor-arg name="key" value="value"/>
     </bean>
     
說明：跟set類似，id一樣是自訂物件名稱，class是類別所在的位置，不同的是constructor-arg。
key需要與前面的參數相同，這樣才能將value注入，
constructor-arg的使用方法很多，這裡示範最基本的用法，另外還有定義型別或者index順序等。

#### ----實例：

     <bean id="personalInfo" class="org.Spring.ConstructorInject.PersonalInfo">
         <constructor-arg name="name" value="Daniel"/>
     </bean>
     
說明：除了本身的名稱外與類別，constructor與setter方式的差別是，不需要set的方式，可以直接將值注入，
可是還是需要給予參數接收值，參數名稱要一樣。

* * *

## GetBean：
雖然已經將物件注入，但是如果沒有取得的方式就無法拿到物件實體，所以通常會寫一個測試方法

      ApplicationContext context =  new ClassPathXmlApplicationContext("Beans.xml");

      PersonalInfo obj = (PersonalInfo) context.getBean("personalInfo");
    
說明：測試方法目前有三種，這麼做是要去抓取Beans.xml，並且讓他作用取得物件實體，

ClassPathXmlApplicationContext後面是beans的路徑，以maven來說必須要放在resources下，
這也是常用的測試方式，另外還有file與web的取得方式，但如果熟悉後通常會直接使用Annotation注入，如此一來便可以取得物件。

範例只要運行App就可以，裏面包含了測試方法。
* * *
# 比較

<table>
    <tr>
        <td>注入方式</td>
	<td>Setter Injection</td>
	<td>Constructor Injection</td>
    </tr>
    <tr>
        <td>注入用法</td>
	<td>定義set方法</td>
	<td>使用建構子注入</td>
    </tr>
    <tr>
        <td>注入判斷</td>
	<td>Set後的名稱</td>
	<td>建構子的參數名稱</td>
    </tr>    
</table>

在使用上雖然沒有一定得使用哪個，可是相對的也有一些優缺點，譬如Setter方式雖然較為複雜，但可以直接在物件中就把值設定好，相對的，
Constructor雖然在物件上較為簡潔，可是卻非常依賴xml內注入值，這樣個有好壞，所以使用上都是可行的。
***
# 總結
花很多時間研究了Spring的控制反轉與依賴注入，事實上是一個很簡單的概念，但是卻不好由程式來說明，其原因是靈活性很高，很難有一套標準，所以我在文件上是以最容易上手的方式，在範例的檔案中並不會有複雜的方式，就只有四個檔案，目的是讓使用者可以更快掌握Spring的Ioc與Di，否則如果要在細說annotation，那勢必又是更深層的講解
