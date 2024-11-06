import logging
from fastapi import Request
from starlette.middleware.base import BaseHTTPMiddleware
from starlette.responses import JSONResponse


class ErrorHandlerMiddleware(BaseHTTPMiddleware):
    async def dispatch(self, request: Request, call_next):
        try:
            response = await call_next(request)
            return response
        except Exception as e:
            logging.error(f"Unhandled error: {e}")
            logging.exception(e)
            return JSONResponse(
                status_code=500,
                content={"error": str(e)}
            )