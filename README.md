# ChatMedia

# Database

## Setup

1. Start docker
2. `docker run --name cassandra-chatmedia -p 9043:9042 -d cassandra:latest`
3. `docker exec -it cassandra-chatmedia cqlsh`
4. `CREATE KEYSPACE IF NOT EXISTS chatmedia_keyspace WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1};`
5. `USE chatmedia_keyspace;`
6. `CREATE TABLE IF NOT EXISTS media_metadata (
    conversation_id UUID,
    media_id UUID,
    media_type TEXT,
    size BIGINT,
    file_location TEXT,
    timestamp TIMESTAMP,
    PRIMARY KEY(conversation_id, timestamp)
) WITH CLUSTERING ORDER BY (timestamp DESC);`
7. Done!