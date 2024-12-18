```toml
name = 'login'
method = 'POST'
url = 'http://localhost:8080/api/v1/auth/login'
sortWeight = 1000000
id = '199115cf-c375-4d47-b188-beb46bc8dc4f'

[body]
type = 'JSON'
raw = '''
{
  "email": "akbar@gmail.com",
  "password": "123456"
}'''
```
