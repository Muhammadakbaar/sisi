```toml
name = 'vendors'
method = 'POST'
url = 'http://localhost:8080/api/v1/vendors'
sortWeight = 2000000
id = 'ef6e2161-4dd8-4c1b-a05d-37e36f4ad60f'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2JhckBnbWFpbC5jb20iLCJpYXQiOjE3MzQ1MzI2MDYsImV4cCI6MTczNDUzNDQwNn0.0-_W_8ZBIQ0zhjHCLUm6yUXEQ_5OSk-G-MeTkdV54SA'

[body]
type = 'JSON'
raw = '''
{
  "name":"bca"
}'''
```
