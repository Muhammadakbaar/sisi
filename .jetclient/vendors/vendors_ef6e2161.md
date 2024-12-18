```toml
name = 'vendors'
method = 'POST'
url = 'http://localhost:8080/api/v1/vendors'
sortWeight = 2000000
id = 'ef6e2161-4dd8-4c1b-a05d-37e36f4ad60f'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2JhckBnbWFpbC5jb20iLCJpYXQiOjE3MzQ1Mzk2NzcsImV4cCI6MTczNDU0MTQ3N30.1-wRs-ydnOtfOaB7XTsDslKAWPt_eoYZnqVDHlHH0Vc'

[body]
type = 'JSON'
raw = '''
{
  "name":"bri"
}'''
```
