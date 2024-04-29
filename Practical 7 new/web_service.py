from flask import Flask, jsonify

app = Flask(__name__)

# Define a route to return some data
@app.route('/api/data', methods=['GET'])
def get_data():
    data = {'message': 'Hello from the web service!'}
    return jsonify(data)

if __name__ == '__main__':
    app.run(debug=True)
