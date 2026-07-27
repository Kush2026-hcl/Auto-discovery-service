CREATE TABLE IF NOT EXISTS discovery_job (

                                             id BIGSERIAL PRIMARY KEY,

                                             job_name VARCHAR(100),

    start_ip VARCHAR(50),

    end_ip VARCHAR(50),

    location VARCHAR(100),

    status VARCHAR(30),

    start_time TIMESTAMP,

    end_time TIMESTAMP,

    created_date TIMESTAMP

    );

CREATE TABLE IF NOT EXISTS discovered_device (

                                                 id BIGSERIAL PRIMARY KEY,

                                                 ip_address VARCHAR(50) UNIQUE,

    host_name VARCHAR(200),

    vendor VARCHAR(100),

    device_type VARCHAR(100),

    sys_descr TEXT,

    sys_object_id VARCHAR(200),

    mac_address VARCHAR(100),

    location VARCHAR(100),

    status VARCHAR(30),

    snmp_version VARCHAR(20),

    created_date TIMESTAMP,

    updated_date TIMESTAMP

    );

CREATE TABLE IF NOT EXISTS snmp_configuration (

                                                  id BIGSERIAL PRIMARY KEY,

                                                  community VARCHAR(100),

    version VARCHAR(20),

    port INTEGER,

    timeout INTEGER,

    retries INTEGER,

    active BOOLEAN

    );

CREATE TABLE IF NOT EXISTS discovery_result (

                                                id BIGSERIAL PRIMARY KEY,

                                                discovery_job_id BIGINT,

                                                ip_address VARCHAR(50),

    status VARCHAR(30),

    message VARCHAR(500),

    discovered_time TIMESTAMP,

    CONSTRAINT fk_job
    FOREIGN KEY (discovery_job_id)
    REFERENCES discovery_job(id)

    );

CREATE TABLE IF NOT EXISTS device_history (

                                              id BIGSERIAL PRIMARY KEY,

                                              ip_address VARCHAR(50),

    status VARCHAR(30),

    remarks VARCHAR(500),

    event_time TIMESTAMP

    );

CREATE TABLE IF NOT EXISTS discovery_scheduler (

                                                   id BIGSERIAL PRIMARY KEY,

                                                   scheduler_name VARCHAR(100),

    start_ip VARCHAR(50),

    end_ip VARCHAR(50),

    location VARCHAR(100),

    cron_expression VARCHAR(100),

    enabled BOOLEAN

    );