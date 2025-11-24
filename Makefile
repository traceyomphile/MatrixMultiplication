BACKEND_DIR=backend
FRONTEND_DIR=frontend

.PHONY: all build up down logs clean backend frontend

# ============================================================
# Local development without Docker
# ============================================================

backend:
	cd $(BACKEND_DIR) && mvn spring-boot:run

frontend:
	cd $(FRONTEND_DIR) && npm run dev

dev:
	make -j2 backend frontend


# ============================================================
# Docker commands
# ============================================================

build:
	docker compose build

up:
	docker compose up

up-d:
	docker compose up -d

down:
	docker compose down

logs:
	docker compose logs -f

restart:
	docker compose down
	docker compose up -d


# ============================================================
# Cleaning
# ============================================================

clean:
	docker compose down --volumes --rmi all
	@echo "Docker environment fully cleaned."

