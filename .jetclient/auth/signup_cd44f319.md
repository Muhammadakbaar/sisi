```toml
name = 'signup'
method = 'POST'
url = 'http://localhost:8080/api/v1/auth/signup'
sortWeight = 500000
id = 'cd44f319-47dd-414b-8b6c-824b6bc7097b'

[auth]
type = 'NO_AUTH'

[body]
type = 'JSON'
raw = '''
{
  "email": "akbar@gmail.com",
  "password": "123456"
}'''
```
