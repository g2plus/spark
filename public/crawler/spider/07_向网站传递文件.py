from requests_toolbelt import MultipartEncoder
import requests

m = MultipartEncoder(
    fields={'field0': 'value', 'field1': 'value',
            'field2': ('filename', open('file.txt', 'rb'), 'text/plain')}
    )

r = requests.post('http://httpbin.org/post', data=m,
                  headers={'Content-Type': m.content_type})

print(r.status_code)

