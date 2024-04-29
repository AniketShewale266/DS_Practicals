import requests

def consume_web_service():
    url = 'http://localhost:5000/api/data'
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        print("Received message:", data['message'])
    else:
        print("Failed to retrieve data from the web service")

if __name__ == '__main__':
    consume_web_service()
