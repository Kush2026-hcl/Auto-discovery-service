INSERT INTO snmp_configuration
(
    community,
    version,
    port,
    timeout,
    retries,
    active
)
VALUES
    (
        'public',
        'v2c',
        161,
        3000,
        2,
        true
    );