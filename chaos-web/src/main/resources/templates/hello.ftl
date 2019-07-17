<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>DreamStudio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        *{margin:0;padding:0;list-style-type:none;}
        body{overflow-x:hidden;}

        html {
            box-sizing: border-box;
        }

        *, *::after, *::before {
            box-sizing: inherit;
        }

        *, *:before, *:after {
            box-sizing: border-box;
            outline: none;
        }

        html {
            font-family: 'Source Sans Pro', sans-serif;
            font-size: 16px;
            font-smooth: auto;
            font-weight: 300;
            line-height: 1.5;
            color: #444;
        }

        body {
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100%;
            height: 100vh;
            background-color: #20293a;
        }

        div {
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            width: 300px;
            height: 300px;
            background-color: #20293a;
            color: #20293a;
            border-radius: 50%;
            font-weight: bold;
            line-height: 1.5;
            text-align: center;
            animation: text 10s ease infinite;
        }
        div:after {
            position: absolute;
            content: "";
            top: -5%;
            left: -5%;
            right: 0;
            z-index: -1;
            height: 100%;
            width: 100%;
            margin: 0 auto;
            transform: scale(0.85);
            filter: blur(5vw);
            background: linear-gradient(270deg, #00fcbd, #7400f9);
            background-size: 150% 150%;
            border-radius: 50%;
            animation: glowmation 10s linear infinite;
        }
        @keyframes glowmation {
            0% {
                top: -3%;
                left: -3%;
                background-position: 0% 50%;
                background-size: 150% 150%;
            }
            12.5% {
                top: -3%;
                left: 0;
                background-size: 70% 30%;
            }
            25% {
                top: -3%;
                left: 3%;
                background-size: 100% 50%;
            }
            37.5% {
                top: 0;
                left: 3%;
                background-size: 70% 30%;
            }
            50% {
                top: 3%;
                left: 3%;
                background-position: 100% 50%;
                background-size: 30% 30%;
            }
            62.5% {
                top: 3%;
                left: 0;
                background-size: 30% 70%;
            }
            75% {
                top: 3%;
                left: -3%;
                background-size: 50% 100%;
            }
            87.5% {
                top: 0;
                left: -3%;
                background-size: 30% 70%;
            }
            100% {
                top: -3%;
                left: -3%;
                background-position: 0% 50%;
                background-size: 150% 150%;
            }
        }
        @keyframes text {
            0% {
                color: #7400f9;
            }
            50% {
                color: #00fcbd;
            }
            100% {
                color: #7400f9;
            }
        }

    </style>
</head>

<body>
<div>Created by DJun.</div>
</body>
</html>