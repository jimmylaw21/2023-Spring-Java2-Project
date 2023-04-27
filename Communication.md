### SpringBoot

https://zhuanlan.zhihu.com/p/115403195

https://juejin.cn/post/7041767631992389645

https://www.jianshu.com/p/ffab3013e1d2

### mybatis

https://juejin.cn/post/7201831345415749692#heading-54

### 现在的项目结构

![img.png](img.png)

### StackOverflow的API key

# SUSTech-2023-Spring-Java2-Project

### Client Id

26107

This Id identifies your application to the Stack Exchange API. Your application client id is **not** secret, and may be safely embeded in distributed binaries.

Pass this as in [our OAuth 2.0 flow](https://api.stackexchange.com/docs/authentication). `client_id`

### Client Secret ([reset](https://stackapps.com/apps/oauth/view/26107#))

1xST3StwUaKctIk6q1UMtw((

Pass this as in [our OAuth 2.0 flow](https://api.stackexchange.com/docs/authentication) if your app uses the explicit path.

This **must be** kept secret. Do not embed it in client side code or binaries you intend to distribute. If you need client side authentication, use the implicit OAuth 2.0 flow. `client_secret`

### Key

qeUQ3LQJnDuzdfxTekiPSg((

Pass this as when making requests against the Stack Exchange API to receive a [higher request quota](https://api.stackexchange.com/docs/throttle).

This is not considered a secret, and may be safely embed in client side code or distributed binaries. `key`

### Description

SUSTech-2023-Spring-Java2-Project

This **text-only** blurb will be shown to users during authentication.

### OAuth Domain

github.com/jimmylaw21/2023-Spring-Java2-Project

Whenever a redirect occurs during an [authentication sessions](https://api.stackexchange.com/docs/authentication) (as specified by ) it must reside under this domain.

For the purposes of redirection, subdomains are considered to be under their parent domain. Registering would allow a redirect_uri of for example. `redirect_uri``example.com``foo.example.com`

### Application Website

https://github.com/jimmylaw21/2023-Spring-Java2-Project

A link to this website will accompany most displays of your application on the Stack Exchange network.

### Application Icon

Not Set

This image will accompany most displays of your application on the Stack Exchange network.

### Stack Apps Post

Not Set

When you've published your application, it should be listed on Stack Apps with the [app](https://stackapps.com/questions/tagged/app) or [script](https://stackapps.com/questions/tagged/script) tags.

### Client Side Flow Is Disabled

An application can either be configured for client side or server side authentication flows.

Changing to one will disable the other flow.

### Desktop OAuth Redirect Uri Is Enabled

Applications that have the client side flow enabled can use as their by default.

This is provided so non-web clients can participate in OAuth 2.0 without requiring a full fledged web server. Applications that do not need this behavior can disable it. `https://stackexchange.com/oauth/login_success``redirect_uri`

[Edit this app](https://stackapps.com/apps/oauth/edit/26107)