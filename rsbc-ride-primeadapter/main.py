from fastapi import FastAPI, Response
import fastapi
from fastapi.responses import PlainTextResponse, JSONResponse

import os
import uvicorn

from src import app

api = fastapi.FastAPI()

def configure_routing():
    api.include_router(app.router)

configure_routing()

if __name__ == '__main__':
    uvicorn.run("main:api", host="0.0.0.0", port=5000, reload=True)