from flask import Flask, request
app = Flask(__name__)

@app.route('/api/sayHello', methods = ['POST'])
def sayHello():
    body = request.get_json()
    print(body)

    responseBody = {
        "version" : "2.0",
        "template" : {
            "outputs" : [
                {
                    "simpleText" : {
                        "text" : "안녕하세요"
                    }
                }
            ]
        }
    }
    return responseBody

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=6000)