
# Tarefas Backend


 ### Backup db
> docker compose exec postgres pg_dump stpdb -U admin > backup-stpdb.sql

### Restore db
> cat backup-stpdb.sql | docker exec -i postgres psql -U admin