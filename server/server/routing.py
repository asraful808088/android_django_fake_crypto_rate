from django.urls import path

from .consumers import GetCryptoCoinRate

url_patterns=[
    path('ws/crypto',GetCryptoCoinRate.as_asgi(),name="crypto_rate")
]